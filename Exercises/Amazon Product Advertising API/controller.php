<?php
    require 'configuration.php';

    // Class for database control operations.
    class DBControl {
        
        function getData() {
            $conn = new mysqli(HOST,DBUSER,DBPASS,DB);
            $query = "SELECT * FROM products";
            $result = $conn->query($query);
            $json = array();
            while($row=$result->fetch_assoc()) {
                $json[]=$row;
            }
            $result->close();
            $conn->close();
            echo json_encode($json);
        }
        
        function addToDB() {
            $conn = new mysqli(HOST,DBUSER,DBPASS,DB);
            // Todo : Need function to escape special chars from MySQL query.
            $query = "INSERT INTO products VALUES (\"".$_GET['ASIN']."\",\"".$_GET['title']."\",\"".$_GET['MPN']."\",\"".$_GET['price']."\")";
            $json = array();
            
            if ($conn->connect_error) {
                $json["status"] = 0;
                die("Connection failed: " . $conn->connect_error);
            }
            if ($conn->query($query) === TRUE) {
                $json["status"] = 1;
            } else {
                $json["status"] = 0;
            }
            
            $conn->close();
            echo json_encode($json);
        }
        
    }

    // Class for Amazon api operations.
    class AMZ {
        
        function getUrl($input) {
            $endpoint = "webservices.amazon.com";
            $uri = "/onca/xml";
            $params = array(
                            "Service" => "AWSECommerceService",
                            "Operation" => "ItemLookup",
                            "AWSAccessKeyId" => AMZ_ACCESS_KEY,
                            "AssociateTag" => AMZ_ASSOCIATE_ID,
                            "ItemId" => $input,
                            "ResponseGroup" => "ItemAttributes,Offers",
                            "IdType" => "ASIN"
                            );
            if (!isset($params["Timestamp"])) {
                $params["Timestamp"] = gmdate('Y-m-d\TH:i:s\Z');
            }
            ksort($params);
            $pairs = array();
            foreach ($params as $key => $value) {
                array_push($pairs, rawurlencode($key)."=".rawurlencode($value));
            }
            $canonical_query_string = join("&", $pairs);
            $string_to_sign = "GET\n".$endpoint."\n".$uri."\n".$canonical_query_string;
            $signature = base64_encode(hash_hmac("sha256", $string_to_sign, AMZ_SECRET_ACCESS_CODE, true));
            $request_url = 'http://'.$endpoint.$uri.'?'.$canonical_query_string.'&Signature='.rawurlencode($signature);
            return $request_url;
        }
        
        function getResults($input) {
            $amazon_url = $this->getUrl($input);
            $json = array();
            $ch = curl_init();
            curl_setopt($ch, CURLOPT_URL, $amazon_url);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
            curl_setopt($ch, CURLOPT_TIMEOUT, '3');
            $content = trim(curl_exec($ch));
            curl_close($ch);
            $doc = new DOMDocument();
            $doc->loadXML($content);
            $json['ASIN'] = $doc->getElementsByTagName('ASIN')->item(0)->nodeValue;
            $json['title'] = $doc->getElementsByTagName('Title')->item(0)->nodeValue;
            $json['MPN'] = $doc->getElementsByTagName('MPN')->item(0)->nodeValue;
            $json['price'] = $doc->getElementsByTagName('FormattedPrice')->item(0)->nodeValue;
            echo json_encode($json);
        }
    }
    
    // Todo : implement better way to parse GET requests.
    switch($_GET['cmd']) {
        case 'getdata':
            $dbctr = new DBControl;
            $dbctr->getData();
            break;
        case 'amzdata':
            $amazon = new AMZ;
            $amazon->getResults($_GET['input']);
            break;
        case 'addtodb':
            $dbctr = new DBControl;
            $dbctr->addToDB();
            break;
        default:
            $json = array();
            $json['status'] = 0;
            echo json_encode($json);
    }

?>
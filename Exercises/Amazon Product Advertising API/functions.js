/*
 * Javascript functions that make ajax calls to the php controller script.
 *
 */
function DisplayDbData() {
    $.ajax({
	url: "controller.php",
	data: "cmd=getdata",
	datatype: "json",
	success: function(data) {
	    var obj = JSON.parse(data);
	    var string = "<table class=\"table\"><tr>";
	    string += "<th>ASIN</th><th>Title</th><th>MPN</th><th>Price</th>";
	    for(i = 0; i < Object.keys(obj).length; i++) {
		string += "<tr><td>" + obj[i].ASIN + "</td>";
		string += "<td>" + obj[i].title + "</td>";
		string += "<td>" + obj[i].MPN + "</td>";
		string += "<td>" + obj[i].price + "</td></tr>";
	    }
	    string += "</table>";
	    document.getElementById("DBContent").innerHTML = string;
	}
    });
}

function addToDB() {
    var ASIN = document.getElementById("ASIN").value;
    var title = document.getElementById("title").value;
    var MPN = document.getElementById("MPN").value;
    var price = document.getElementById("price").value;
    $.ajax({
	url: "controller.php",
	data: "cmd=addtodb&ASIN=" + ASIN + "&title=" + title + "&MPN=" + MPN + "&price=" + price,
	datatype: "json",
	success: function(data) {
	    var obj = JSON.parse(data);
	    var string = "";
	    if ( obj.status == 1 ) {
		string = "Successfuly added data to Database.";
		DisplayDbData();
	    } else if ( obj.status == 0 ) {
		string = "Error adding to Database.";
	    }
	    document.getElementById("AMZContent").innerHTML = string;
	}
    });
}

function searchAMZ() {
    var input = document.getElementById("itemId").value;
    $.ajax({
	url: "controller.php",
	data: "cmd=amzdata&input=" + input,
	datatype: "json",
	success: function(data) {
	    var obj = JSON.parse(data);
	    var string = "";
	    string += "<input type=\"hidden\" id=\"ASIN\" value=\""+ obj.ASIN + "\">";
	    string += "<input type=\"hidden\" id=\"title\" value=\""+ obj.title + "\">";
	    string += "<input type=\"hidden\" id=\"MPN\" value=\""+ obj.MPN + "\">";
	    string += "<input type=\"hidden\" id=\"price\" value=\""+ obj.price + "\">";
	    string += "ASIN : " + obj.ASIN + "<br>";
	    string +="Title : " + obj.title + "<br>";
	    string +="MPN : " + obj.MPN + "<br>";
	    string +="Price : " + obj.price + "<br>";
	    string += "<input type=\"button\" onClick=\"addToDB()\" value=\"Insert into Database\">";
	    document.getElementById("AMZContent").innerHTML = string;
	}
    });
}

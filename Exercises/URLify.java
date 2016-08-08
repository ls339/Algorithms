public class URLify {

    // Using a string buffer
    public String run(String s, Integer len) {
	char[] content = s.toCharArray();
	StringBuffer sb = new StringBuffer();
	for(int i=0;i<len;i++) {
	    if(content[i]==' ') {
		sb.append("%20");
		continue;
	    }
	    sb.append(content[i]);
	}
	return sb.toString();
    }

    public void replaceSpaces(char[] str,int TrueLength) {
	int SpaceCount = 0, index, i = 0;
	for(i=0;i<TrueLength;i++) {
	    if(str[i]==' ') SpaceCount++;
	}
	index = TrueLength + SpaceCount * 2;
	if(TrueLength < str.length) str[TrueLength] = '\0';
	for(i=TrueLength - 1;i>=0;i--) {
	    if(str[i]==' ') {
		str[index-1] = '0';
		str[index-2] = '2';
		str[index-3] = '%';
		index = index - 3;
	    } else {
		str[index-1] = str[i];
		index--;
	    }
	}
    }

    public static void main(String[] args) {

	String s = "Mr John Smith    ";
	char[] str = s.toCharArray();
	Integer len = 13;
	URLify myURL = new URLify();
	System.out.print("Input String = \""+s+"\"\nInput length = "+len+"\n");
	myURL.replaceSpaces(str,len);
	System.out.println("Output String = \""+new String(str)+"\"");
    }

}

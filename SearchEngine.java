import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class Handle implements URLHandler{
    
    public String handleRequest(URI url){
        ArrayList<String> str = new ArrayList<>();

        if(url.getPath().equals("/add")){
            System.out.println(url.getQuery()); //trace
            if(url.getQuery().substring(0,2).equals("s=")){
                String[] quer = url.getQuery().split("=");
                str.add(quer[1]);
                return "Your list is: " + str.toString();
            }
        }
        else if(url.getPath().equals("/search")){
            if(url.getQuery().substring(0,2).equals("s=")){
                String[] quer2 = url.getQuery().split("=");
                if(str.contains(quer2[1])){
                    return "You're looking for: " + quer2[1];
                }
            }
            else{
                return "Sorry, I'm having a hard time completing your search :(";
            }
        }
        return "Command does not yet exist, come back later...";
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handle());
    }
}
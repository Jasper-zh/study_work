package serverModule.server;

public class ServerThread extends Thread {

    ServerMain serverMain = null;

    public ServerThread(ServerMain serverMain) {
        this.serverMain = serverMain;
    }


    @Override
    public void run(){
        System.out.println("来到线程");

    }
}

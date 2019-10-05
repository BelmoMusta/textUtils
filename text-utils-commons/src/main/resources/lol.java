class Client {
    private int idClient;
    public int getIdClient();
    public void setIdClient(int idClient);
    private String nom;
    public static int getNextClientId();
    private class AdressClient {
        String rue;
        String ville;
    }
    private AdressClient adressClient;
}
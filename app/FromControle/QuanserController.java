//package FromControle;
//
//import br.ufrn.dca.controle.QuanserClient;
//import br.ufrn.dca.controle.QuanserClientException;
//
//public class QuanserController {
//
//    public QuanserClient quanserClient;
//    public int porta;
//    public String endereco;
//
//    public boolean conect(String end, int p) {
//        try {
//            System.out.println("tentando conectar....");
//            quanserClient = new QuanserClient(end, p);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    public double read(int channel) {
//        double x = -1;
//        if (quanserClient != null) {
//            try {
//                x = quanserClient.read(channel);
//            } catch (Exception e) {
//                System.out.println("Erro de leitura");
//            }
//        }
//        return x;
//    }
//
//    public void write(int channel, double x) {
//        if (quanserClient != null) {
//            try {
//                quanserClient.write(channel, x);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//}
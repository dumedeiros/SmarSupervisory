//    package FromControle;
//
//import java.text.Format;
//
///**
// *
// */
//public class Onda {
//
//    public Onda() {
//    }
//
//    public enum Formato {
//
//        DEGRAU, SENO, COSSENO, QUADRADA, DENTE_SERRA, ALEATORIA
//    }
//    public Onda.Formato formato;
//    public double amplitude;
//    private double pico;
//    public double offset;
//    public int tempo;
//    public int periodo;
//    public long timeAnt;
//    public double out;
//    private final double PI = Math.PI;
//
//    public Onda(int periodo, double amplitude, double offset, Onda.Formato waveFormat) {
//        this.periodo = periodo;
//        this.amplitude = amplitude;
//        this.offset = offset;
//        this.formato = waveFormat;
//        this.tempo = 0;
//        timeAnt = System.currentTimeMillis();
//        this.pico = amplitude / 2;
//    }
//
//    public void setParametros(int tipoOnda, double amplitude, int periodo, double offset) {
//        this.periodo = periodo;
//        this.timeAnt = System.currentTimeMillis();
//        this.tempo = 0;
//        this.amplitude = amplitude;
//        this.pico = amplitude / 2;
//        this.offset = offset;
//        switch (tipoOnda) {
//            case 0:
//                this.formato = Onda.Formato.DEGRAU;
//                break;
//            case 1:
//                this.formato = Onda.Formato.SENO;
//                break;
//            case 2:
//                this.formato = Onda.Formato.COSSENO;
//                break;
//            case 3:
//                this.formato = Onda.Formato.QUADRADA;
//                break;
//            case 4:
//                this.formato = Onda.Formato.DENTE_SERRA;
//                break;
//            case 5:
//                this.formato = Onda.Formato.ALEATORIA;
//                break;
//        }
//    }
//
//    public double nextPointControled(double altura) {
//        update();
//        out = offset + evaluate(tempo);
//        out = intertravamento(out, altura);
//        return out;
//    }
//
//    private double intertravamento(double x, double altura) {
//        if (x > 3) {
//            x = 3;
//        }
//        if (x < -3) {
//            x = -3;
//        }
//        if (altura >= 27) {
//            x = 0;
//        }
//        if ((altura <= 4) && (x < 0)) {
//            x = 0;
//        }
//        return x;
//
//    }
//
//    public double evaluate(int t) {
//        switch (formato) {
//            case SENO:
//                return pico * Math.sin((2 * PI * t) / periodo);
//            case COSSENO:
//                return pico * Math.cos((2 * PI * t) / periodo);
//            case QUADRADA:
//                return ((1.0 * t) / periodo) <= 0.5 ? amplitude / 2 : (-amplitude / 2);
//            case DENTE_SERRA:
//                return ((amplitude * t) / periodo);
//            case DEGRAU:
//                return (amplitude);
//            case ALEATORIA:
//                return (Math.random() * amplitude);
//            default:
//                return 0;
//        }
//    }
//
//    public void update() {
//        long taux = System.currentTimeMillis();
//        tempo += taux - timeAnt;
//        timeAnt = taux;
//        tempo = tempo % periodo;
//    }
//
//}

//package FromControle;
//
//public class PID {
//
//    //Parametros
//    public double kp;
//    public double ki;
//    public double kd;
//    public int Itype;
//    public int contrType;
//    public double T; //Periodo de Amostragem
//    
//    //Estados
//    public double setPoint;
//    public double erro;
//    public double erroAnterior;
//    public double alturaAnterior;
//    public double I;
//    
//    
//    //Estatisticas
//    public double overshoot;
//    public long tovershoot;
//    public long tsubida;
//    public long tacomodacao;
//    public boolean acomodated;
//    public long tinicial;//Não entra na interf.
//    public boolean maxOvershoot;//Não entra na interf.
//    public double percenOvshoot;
//    
//    public double MAX_OUT;
//    public double MIN_OUT;
//
//    public PID() {
//        resetStates();
//        this.Itype = 0;
//        this.contrType = 0;
//        this.maxOvershoot = true;
//        this.T = 1;
//        MAX_OUT = 3;
//        MIN_OUT = -3;
//        initStatistics();
//    }
//
//    private void resetStates(){
//        this.setPoint = 0;
//        this.erro = 0;
//        this.erroAnterior = 0;
//        this.alturaAnterior = 0;
//        this.I = 0;
//    }
//    
//    public PID(double kp, double ki, double kd, double T, double setPoint, int Itype) {
//        resetStates();
//        
//        this.kp = kp;
//        this.ki = ki;
//        this.kd = kd;
//        this.T = T;
//        this.Itype = Itype;
//        this.contrType = 0;
//        maxOvershoot = true;
//        
//        initStatistics();
//    }
//
//    public void initStatistics() {
//        if (maxOvershoot) {
//            overshoot = -100000;
//        } else {
//            overshoot = 100000;
//        }
//        tinicial = System.currentTimeMillis();
//        tacomodacao = tovershoot = tsubida = 0;
//        acomodated = false;
//    }
//
//    public void calculateStatistics(double altura) {
//        if (maxOvershoot && (altura > overshoot)) {
//            overshoot = altura;
//            percenOvshoot = (overshoot - setPoint) / setPoint * 100;
//            tovershoot = System.currentTimeMillis() - tinicial;
//        }
//        if (!maxOvershoot && (altura < overshoot)) {
//            overshoot = altura;
//            percenOvshoot = (setPoint - overshoot) / setPoint * 100;
//            tovershoot = System.currentTimeMillis() - tinicial;
//        }
//        if (tsubida == 0) {
//            if ((altura > setPoint) && maxOvershoot) {
//                tsubida = System.currentTimeMillis() - tinicial;
//            }
//            if ((altura < setPoint) && !maxOvershoot) {
//                tsubida = System.currentTimeMillis() - tinicial;
//            }
//        }
//        if ((altura < 1.02 * setPoint) && (altura > 0.98 * setPoint)) {
//            if (!acomodated) {
//                tacomodacao = System.currentTimeMillis() - tinicial;
//                acomodated = true;
//            }
//        } else {
//            acomodated = false;
//            tacomodacao = 0;
//        }
//    }
//
////    public void setParametros(double kp, double ki, double kd, double T, double setPoint, int Itype, boolean pi_d) {
//    public void setParametros(double kp, double ki, double kd, double T, double setPoint, int Itype, int contrType) {
//        this.kp = kp;
//        this.ki = ki;
//        this.kd = kd;
//        this.T = T;
//        if (this.setPoint <= setPoint) {
//            maxOvershoot = true;
//        } else {
//            maxOvershoot = false;
//        }
//        this.setPoint = setPoint;
//        this.Itype = Itype;
//        this.contrType = contrType;
//        
//        ajustarTipoControlador(contrType);
//        initStatistics();
//    }
//
//    //Retorna o sinal a ser escrito;
//    public double processData(double altura, double alturaTravamento) {
//        double p, d, pid, i; //Em volts
//        erroAnterior = erro;
//        erro = setPoint - altura;
////        System.out.println("Erro: " + erro);
//        p = kp * erro;
////        System.out.println("kp: " + p);
//
//        i = I + ki * erro * T;
//        System.out.println("I: " + I);
//
//        if (contrType == 4) {//PI_D
//            d = kd * (altura - alturaAnterior) / T;
//            System.out.println("d: " + d);
//        } else {
//            d = kd * (erro - erroAnterior) / T;
//        }
//
//        pid = p + i + d;
//        MalhaFechada.systemData.ganhoP = p;
//        MalhaFechada.systemData.ganhoI = I;
//        MalhaFechada.systemData.ganhoD = d;
//        MalhaFechada.systemData.ganhoPID = pid;
//
//        //Intertravamentos
//        double out = intertravamento(pid, alturaTravamento);
//
//        if (out != pid) {
//            if (Itype == 0) {
//                I = i;
//            }
//            if (Itype == 1) {
//                if (i * erro < 0) {
//                    I = i; //Erro e integral com sinais distintos
//                }
//            }
//            if (Itype == 2) {
//                I = 0;
//            }
//        } else {
//            I = i;
//        }
//
//        alturaAnterior = altura;
//        calculateStatistics(altura);
//        System.out.println(contrType);
//        return out;
//    }
//
//    private double intertravamento(double pid, double altura) {
//        if (pid > MAX_OUT) {
//            pid = MAX_OUT;
//        }
//        if (pid < MIN_OUT) {
//            pid = MIN_OUT;
//        }
//        if (altura >= 27) {
//            pid = 0;
//        }
//        if ((altura <= 4) && (pid < 0)) {
//            pid = 0;
//        }
//        return pid;
//    }
//
//    private void ajustarTipoControlador(int contrType) {
//        if (contrType == 0) {
//            I = 0;
//            ki = 0;
//            kd = 0;
//        }
//        if (contrType == 1) {
//            kd = 0;
//        }
//        if (contrType == 2) {
//            I = 0;
//            ki = 0;
//        }
//    }
//}

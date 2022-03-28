import java.util.concurrent.Semaphore;

public class Controller extends Thread {
    private int idoperacao;
    private Semaphore semaforo1;
    private Semaphore semaforo2;
    private static int codconta;
    private static int salconta;
    private int valortransição;

    public Controller(int idoperacao, Semaphore semaforo1, Semaphore semaforo2, int codconta, int salconta, int valortransição) {
        this.idoperacao = idoperacao;
        this.semaforo1 = semaforo1;
        this.semaforo2 = semaforo2;
        this.codconta = codconta;
        this.salconta = salconta;
        this.valortransição = valortransição;
    }

    public void run() {
        if (idoperacao == 0) {
            try {
                semaforo1.acquire();
                operacaoSaque();
            } catch (Exception e) {
            } finally {
                operacaoFin();
                semaforo1.release();
            }
        } else {
            try {
                semaforo2.acquire();
                operacaoDeposito();
            } catch (Exception e) {
            } finally {
                operacaoFin();
                semaforo2.release();
            }
        }

    }

    private void operacaoSaque() {
        System.out.println("Efetuando o Saque na conta de código: " + codconta + " saldo atual da conta: " + salconta + " valor de saque: " + valortransição);
        try {
            salconta = salconta - valortransição;
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Efetuado o saque!, saldo da conta: " + salconta);
    }

    private void operacaoDeposito() {
        System.out.println("Efetuando o depósito na conta de cod: " + codconta + " saldo atual da conta: " + salconta + " valor de deposito: " + valortransição);
        try {
            salconta = salconta + valortransição;
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Efetuado o deposito!, saldo da conta: " + salconta);
    }


    private void operacaoFin() {
        String operacao = "";
        if (idoperacao == 0) {
            operacao = "Saque";
        } else {
            operacao = "Deposito";
        }
        System.out.println("Operação " + operacao + " finalizada");

    }
}
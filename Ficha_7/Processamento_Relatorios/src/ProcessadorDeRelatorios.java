class ProcessadorDeRelatorios implements Runnable {
    private static final ThreadLocal<Integer> userId = new ThreadLocal<>();
    private final int id;

    public ProcessadorDeRelatorios(int id) {
        this.id = id;
    }

    public static void setUserId(int i) {
    }

    @Override
    public void run() {
        userId.set(id); // Configura o UserID para o ThreadLocal no início do thread
        Integer currentUserId = userId.get(); // Obtém o valor configurado
        if (currentUserId == null) {
            throw new RuntimeException("UserID não configurado para o thread atual.");
        }

        // Lógica de processamento do relatório
        Relatorio relatorio = new Relatorio(currentUserId, System.currentTimeMillis(),
                "Conteúdo do relatório das " + (currentUserId % 2 == 0 ? "propinas" : "matrículas"));
        System.out.println("Relatório processado: " + relatorio);
    }
}
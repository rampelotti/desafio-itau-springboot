package desafio.itau.demo.model;

@Service
public TransactionService {
    
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>(); //biblioteca thread-safe

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream() //expressão lambda
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60))) //filtra transações dos últimos 60 segundos
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}
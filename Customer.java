class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();
    
    public Customer(String name) {
        this.name = name;
    }
    
    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    
    public String getName() {
        return name;
    }
    
    public String statement() {
        return getHeader() + getRentalLines() + getFooter();
    }
    
    private String getHeader() {
        return "Rental Record for " + getName() + "\n";
    }
    
    private String getRentalLines() {
        String result = "";
        for (Rental rental : rentals) {
            result += getRentalLine(rental);
        }
        return result;
    }
    
    private String getRentalLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" 
             + rental.getCharge() + "\n";
    }
    
    private String getFooter() {
        return getAmountOwedLine() + getFrequentRenterPointsLine();
    }
    
    private String getAmountOwedLine() {
        return "Amount owed is " + getTotalCharge() + "\n";
    }
    
    private String getFrequentRenterPointsLine() {
        return "You earned " + getTotalFrequentRenterPoints() 
             + " frequent renter points";
    }
    
    private double getTotalCharge() {
        double total = 0;
        for (Rental rental : rentals) {
            total += rental.getCharge();
        }
        return total;
    }
    
    private int getTotalFrequentRenterPoints() {
        int points = 0;
        for (Rental rental : rentals) {
            points += rental.getFrequentRenterPoints();
        }
        return points;
    }
}

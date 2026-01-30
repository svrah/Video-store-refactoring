class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    
    private String title;
    private int priceCode;
    
    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }
    
    public int getPriceCode() {
        return priceCode;
    }
    
    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getCharge(int daysRented) {
        double amount = 0;
        switch (priceCode) {
            case REGULAR:
                amount += 2;
                if (daysRented > 2)
                    amount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                amount += daysRented * 3;
                break;
            case CHILDRENS:
                amount += 1.5;
                if (daysRented > 3)
                    amount += (daysRented - 3) * 1.5;
                break;
        }
        return amount;
    }
    
    public int getFrequentRenterPoints(int daysRented) {
        if (priceCode == NEW_RELEASE && daysRented > 1) {
            return 2;
        }
        return 1;
    }
}

class Rental {
    private Movie movie;
    private int daysRented;
    
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public int getDaysRented() {
        return daysRented;
    }
    
    public double getCharge() {
        return movie.getCharge(daysRented);
    }
    
    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }
}

import java.util.*;

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
        String result = "Rental Record for " + getName() + "\n";
        
        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" 
                   + rental.getCharge() + "\n";
        }
        
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() 
               + " frequent renter points";
        return result;
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

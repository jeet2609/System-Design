/*
    1. Software Entities (classes, modules, functions, etc) should be open for extension, but closed for modification
*/

class DiscountCalculator_ {
    public double calculateDiscount(double amount) {
        return amount * 9.0;
    }
}

/*
    1. Now store decides to give 20% discont to VIP members.
    2. If we try to modify logie inside the Discount Calculator class to add new discount, you are violating the open-closed Principle.
    3. So right way is
*/


abstract class DiscountCalculator {
    public abstract double calculateDiscount(double amount);
}

class RegularDiscountCalculator extends DiscountCalculator {
    public double calculateDiscount(double amount) {
        return amount * 9.0;
    }
}

class VIPDiscountCalculator extends DiscountCalculator {
    public double calculateDiscount(double amount) {
        return amount * 8.0;
    }
}

/*
    1. Now in future if we want 30% discount for Super VIP members
    2. We can extend AbstractDiscountCalculator and Create SuperVIPDiscountCalculator class
*/
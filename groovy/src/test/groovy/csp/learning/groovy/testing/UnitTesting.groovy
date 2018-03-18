package csp.learning.groovy.testing

import groovy.mock.interceptor.MockFor

class UnitTesting extends GroovyTestCase {

    private BankAccount account

    @Override
    void setUp() {
        account = new BankAccount(10)
    }

    void testCannotHaveNegativeOpeningBalance() {
        shouldFail(IllegalArgumentException) {
            new BankAccount(-5)
        }
    }

    void testCanDepositMoney() {
        account.deposit(5)
        assert account.balance == 15
    }

    void testCannotDepositNegativeAmount() {
        shouldFail(IllegalArgumentException) {
            account.deposit(-5)
        }
    }

    void testCanWithdrawMoney() {
        account.withdraw(5)
        assert account.balance == 5
    }

    void testCannotWithdrawNegativeAmount() {
        shouldFail(IllegalArgumentException) {
            account.withdraw(-5)
        }
    }

    void testCannotWithdrawMoreThanBalance() {
        shouldFail(InsufficientFundsException) {
            account.withdraw(15)
        }
    }

    void testCanAccrueInterest() {
        def service = new MockFor(InterestRateService)

        service.demand.getInterestRate {
            return 0.1
        }

        service.use {
            account.accrueInterest()
        }

        assert account.balance == 11
    }

    private class BankAccount {

        private balance

        BankAccount(openingBalance) {
            requireNonNegative(openingBalance)
            balance = openingBalance
        }

        private void setBalance(balance) {
            // prevent setting
        }

        void deposit(amount) {
            requireNonNegative(amount)
            balance += amount
        }

        void withdraw(amount) {
            requireNonNegative(amount)
            if (amount > balance) {
                throw new InsufficientFundsException()
            }
            balance -= amount
        }

        void accrueInterest() {
            def service = new InterestRateService()
            def rate = service.interestRate
            def accruedInterest = balance * rate
            deposit(accruedInterest)
        }

        private static void requireNonNegative(quantity) {
            if (quantity < 0) {
                throw new IllegalArgumentException()
            }
        }

    }

    private class InsufficientFundsException extends Exception {

    }

    private class InterestRateService {
        double getInterestRate() {
            // should come from web service
            return 0.5
        }
    }

}

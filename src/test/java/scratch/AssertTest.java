package scratch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class AssertTest {

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }

    }

    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertThat(account.hasPositiveBalance()).isTrue();
    }

    @Test
    public void depositIncreaseBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertThat(account.getBalance() > initialBalance).isTrue();
        assertThat(account.getBalance()).isEqualTo(100);
    }

    @Test
    public void throwsWhenWithdrawingTooMuch(){
        assertThatThrownBy(() -> { account.withdraw(100); })
                .isInstanceOf(InsufficientFundsException.class).hasMessage("balance only 0");
    }

    @Test
    public void comparesArraysPassing(){
        assertThat(new String[] {"a", "b"}).containsExactly("a", "b");
    }

    @Test
    public void comparesCollectionsPassing() {
        assertThat(Arrays.asList(new String[] {"a"})).containsExactly("a");
    }

    @Test
    public void assertDoublesCloseTo() {
        assertThat(2.32 * 3).isCloseTo(6.96, within(0.0005));
    }
}
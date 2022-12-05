package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoinMachineTest {

  private CoinMachine coinMachine;

  @BeforeEach
  void setup() {
    coinMachine = new CoinMachine();
  }

  @Test
  void should_init_coin_machine() {
    coinMachine.putMoneyInside("100x50");
    double total = coinMachine.totalMoney();
    assertEquals(5000, total);
  }

  @Test
  void should_init_coin_machine_with_multiple_deposit(){
    coinMachine.putMoneyInside("100x50");
    coinMachine.putMoneyInside("50x20");
    double total = coinMachine.totalMoney();
    assertEquals(6000, total);
  }

  @Test
  void should_check_exact_money_remaining(){
    coinMachine.putMoneyInside("100x50");
    coinMachine.putMoneyInside("50x20");
    assertEquals(100, coinMachine.check(50));
    assertEquals(50, coinMachine.check(20));
  }

  @Test
  void should_get_money(){
    coinMachine.putMoneyInside("100x50");
    coinMachine.putMoneyInside("50x20");
    coinMachine.getMoney(160);
    assertEquals(97, coinMachine.check(50));
    assertEquals(50, coinMachine.check(20));
  }

}

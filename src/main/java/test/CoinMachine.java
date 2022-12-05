package test;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CoinMachine implements ICoinMachine {

	Map<Integer, Coin> coins;

	public CoinMachine() {
		coins = new TreeMap<Integer, Coin>(Collections.reverseOrder());
	}

	@Override
	public void putMoneyInside(String strCoinMachine) {
		String[] string = strCoinMachine.split("x");
		coins.put(Integer.parseInt(string[1]),
				Coin.builder().type(Integer.parseInt(string[1])).quantite(Integer.parseInt(string[0])).build());
	}

	@Override
	public int totalMoney() {
		int total = 0;
		total = coins.entrySet().stream()
				.collect(Collectors.summingInt(e -> e.getValue().getType() * e.getValue().getQuantite()));

		return total;
	}

	@Override
	public int check(int typeCoin) {
		return coins.get(typeCoin).getQuantite();
	}

	@Override
	public void getMoney(int montant) {
		int division, modulo, total = montant;

		for (Map.Entry<Integer, Coin> entry : coins.entrySet()) {
			division = total / entry.getKey();
			modulo = total % entry.getKey();
			coins.put(entry.getKey(),
					Coin.builder().type(entry.getKey()).quantite(entry.getValue().getQuantite() - division).build());
			total = modulo;
			if (modulo == 0)
				break;
		}
	}
}

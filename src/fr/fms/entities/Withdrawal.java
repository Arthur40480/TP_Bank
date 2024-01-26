/**
 * @author El babili - 2023
 * 
 */

package fr.fms.entities;

import java.util.Date;

public class Withdrawal extends Transaction {

	public Withdrawal(long transactionId, Date transactionDate, double amount, long accountId) {
		super(transactionId, transactionDate, amount, accountId);
	}
	
	@Override
	public String toString() {
		return "Retrait : " + super.toString();
	}
}

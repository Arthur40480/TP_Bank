/**
 * @author El babili - 2023
 * @version 1.0
 * 
 */

package fr.fms.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Account {
	private long accountId;
	private Date creationDate;
	private double balance;
	private Customer customer;
	static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private ArrayList<Transaction> listTransactions;
	
	public Account(long accountId, Date creationDate, double balance, Customer customer) {
		this.accountId = accountId;
		this.creationDate = creationDate;
		this.balance = balance;
		//Todo : gestion du cas particulier de client non instancié !
		this.customer = customer;
		this.customer.getListAccounts().add(this);		//lorsque j'ajoute un compte à un client, 
														//j'ajoute à la liste de comptes du client ce nouveau compte
		this.listTransactions = new ArrayList<Transaction>();
	}
	
	/**
	 * Méthode qui permet de vérifier le montant à retirer
	 * @param scanner correspond à l'objet Scanner
	 * @return amount correspond au montant que le client veux retirer
	 */
	public static double isWithdrawalPossible(Scanner scanner, String message) {
		double amount;
		System.out.println(message);
		while(true) {
			try {
				amount = scanner.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.println("Saissisez un montant valide");
				scanner.next();
			}
		}
		return amount;
	}
	
	@Override
	public String toString() {
		return " [accountId=" + accountId + ", creationDate=" + SIMPLE_DATE_FORMAT.format(creationDate) + ", balance=" + balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getListTransactions() {
		return listTransactions;
	}
}

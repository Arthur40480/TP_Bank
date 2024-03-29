/**
 * Version 1.0 d'une appli bancaire simplifiée offrant la possibilitée de créer des clients, des comptes bancaires associés et des opérations ou
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 
 * + permet d'afficher l'historique des transactions sur un compte
 * + la gestion des cas particuliers est rudimentaire ici puisque la notion d'exception n'a pas encore été abordée
 * 
 * @author El babili - 2023
 * 
 */

package fr.fms;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.fms.business.IBankImpl;
import fr.fms.entities.Account;
import fr.fms.entities.Current;
import fr.fms.entities.Customer;
import fr.fms.entities.Saving;
import fr.fms.entities.Transaction;

public class MyBankApp {	
	public static void main(String[] args) {
//		//représente l'activité de notre banque
		IBankImpl bankJob = new IBankImpl();
//		
//		System.out.println("création puis affichage de 2 comptes bancaires");
		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");		
		Current firstAccount = new Current(100200300, new Date(), 1500, 200 , robert);
		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);
		
		bankJob.addAccount(firstAccount);
		bankJob.addAccount(secondAccount);
		
//		System.out.println(firstAccount);
//		System.out.println(secondAccount);		
//		

//		
//		//banquier ou client
//		bankJob.pay(firstAccount.getAccountId(),500);		// versement de 500 euros sur le compte de robert
//		bankJob.pay(secondAccount.getAccountId(), 1000);	// versement de 1000 euros sur le compte de julie
//		
//		//banquier ou client
//		bankJob.withdraw(100200300, 250);			// retrait de 250 euros sur le compte de robert
//		bankJob.withdraw(200300400, 400);			// retrait de 400 euros sur le compte de julie
//		
//		//banquier ou client
//		bankJob.transfert(firstAccount.getAccountId(), 200300400, 200);		// virement de robert chez julie de 200
//		System.out.println("----------------------------------------------------------");
//		System.out.println("solde de "+ firstAccount.getCustomer().getName() + " : " + bankJob.consultAccount(firstAccount.getAccountId()).getBalance());
//		System.out.println("solde de "+ secondAccount.getCustomer().getName() + " : "+ bankJob.consultAccount(secondAccount.getAccountId()).getBalance());
//		System.out.println("----------------------------------------------------------");
//		bankJob.consultAccount(111111);		//test du compte inexistant
//		bankJob.withdraw(100200300, 10000);	//test capacité retrait dépassée
//		bankJob.transfert(100200300, 100200300, 50000);		//test virement sur le même compte
//		
//		//banquier
//		bankJob.addAccount(firstAccount);	//test rajout du même compte au même client
//		bankJob.addAccount(new Current(300400500, new Date(), 750, 150 , julie));	//ajout nouveau compte à Julie		
//		System.out.println("\n-----------------------Liste des comptes de ma banque-----------------------------------");
//		for(Account acc : bankJob.listAccounts())
//			System.out.println(acc);
//		System.out.println("\n-----------------------Liste des comptes de julie-----------------------------------");
//		for(Account acc : julie.getListAccounts()) {
//			System.out.println(acc);
//		}
//		
//		//banquier ou client
//		System.out.println("\n-------------------liste des transactions de l'unique compte de robert------------------------");
//		for(Transaction trans : bankJob.listTransactions(100200300))
//			System.out.println(trans);
//		System.out.println("-------------------liste des transactions du compte N° 200300400 de Julie------------------------");
//		for(Transaction trans : bankJob.listTransactions(200300400))
//			System.out.println(trans);
			
		Scanner scanner = new Scanner(System.in);
		userConnect(scanner, bankJob);
			
	}
	
	/**
	 * Méthode qui représente la connexion de l'utilisateur à sont compte
	 * @param scanner correspond à l'objet Scanner
	 * @param bankJob fait référence à la banque associée à ce compte
	 * @param message fait référence au message qui s'affiche pour indiquer les instructions à l'utilisateur
	 */
	public static void userConnect(Scanner scanner, IBankImpl bankJob) {
		System.out.println("------------------- LA BANQUE DES PAUVRES ------------------------");
		Account account = getUserAccount(scanner, bankJob, "Saisissez un numéro de compte bancaire valide");
		System.out.println();
		System.out.println("Bienvenu " + account.getCustomer().getFirstName() + ", que souhaitez vous faire ?");
		displayMenu(scanner, account, bankJob);
	}
	
	/**
	 * Méthode qui de renvoyer le compte de l'utilisateur en ayant vérifier sa saisie
	 * @param scanner correspond à l'objet Scanner
	 * @param bankJob fait référence à la banque associée à ce compte
	 * @param message fait référence au message qui s'affiche pour indiquer les instructions à l'utilisateur
	 * @return account qui fait référence au compte sur lequel le client viens de se connecter
	 */
	public static Account getUserAccount(Scanner scanner, IBankImpl bankJob, String message) {
		int accountId;
		Account account;
		System.out.println(message);
		while(true) {
			try {
				accountId = scanner.nextInt();
				account = bankJob.consultAccount(accountId);
				if(account != null) {
					break;
				} else {
					System.out.println("Saisissez un numéro de compte bancaire valide");
				}
			} catch(InputMismatchException e) {
				System.out.println("Erreur : veuillez saisir un numéro de compte valide");
				scanner.next();
			}
		}
		return account;
	}
	
	/**
	 * Méthode qui permet d'afficher le menu et d'apeller la méthode correspondante au choix de l'utilisateur
	 * @param scanner correspond à l'objet Scanner
	 * @param account qui fait référence au compte sur lequel les opérations vont être effectuer
	 */
	public static void displayMenu(Scanner scanner, Account account, IBankImpl bankJob) {
		int userChoice;
		System.out.println("------------------- taper le numéro correspondant -----------------------");
		System.out.println("1:Versement - 2:Retrait - 3:Virement - 4:Information sur ce compte - 5:Liste des opérations - 6:Sortir");
		
		while(true) {
			try {
				userChoice = scanner.nextInt();
				if(userChoice > 0 && userChoice < 7) {
					break;
				} else {
					System.out.println("Erreur : veuillez saisir numéro de choix valable (1 - 6)");
				}
			} catch(InputMismatchException e) {
				System.out.println("Erreur : veuillez saisir numéro de choix valable (1 - 6)");
				scanner.next();
			}
		}
		
		switch(userChoice) {
			case 1:
				double payAmount = Account.isValidAmount(scanner, "Saisissez le montant à verser sur le compte");
				bankJob.pay(account.getAccountId(), payAmount);
				displayMenu(scanner, account, bankJob);
				break;
			case 2:
				double withdrawAmount = Account.isValidAmount(scanner, "Saissisez le montant à retirer sur ce compte");	
				bankJob.withdraw(account.getAccountId(), withdrawAmount);	
				displayMenu(scanner, account, bankJob);
				break;
			case 3:
				Account destinationAccount = getUserAccount(scanner, bankJob, "Saisissez le numéro de compte destinataire");
				double transfertAmout = Account.isValidAmount(scanner, "Saisissez le montant à virer sur ce compte");		
				bankJob.transfert(account.getAccountId(), destinationAccount.getAccountId(), transfertAmout);
				displayMenu(scanner, account, bankJob);
				break;
			case 4:
				System.out.println(account);
				System.out.println();				
				displayMenu(scanner, account, bankJob);
				break;
			case 5:
				account.getListTransactions().stream()
					.forEach(transaction -> System.out.println(transaction));
				System.out.println();
				displayMenu(scanner, account, bankJob);
				break;
			case 6:
				System.out.println("Deconnexion réussie");
				System.out.println();
				userConnect(scanner, bankJob);
				break;			
		}
	}
}

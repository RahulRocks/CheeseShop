import java.util.Scanner;

public class Shop 
{
	private static final Scanner kb = new Scanner(System.in);

	static String [] names = new String [100];
	static double [] pricePerPackage = new double[100];
	static int discount[] = new int [100];
	static double additionalDiscountAmount = 0.0;
	static double additionalDiscountRate = 0.0;
	static int productQuantity[] = new int [100];
	
	
	public static void intro()
	{

		System.out.println("This program supports 4 functions:");
		System.out.print( "    "+ "1. Shop Setup");
		System.out.println("");
		System.out.print( "    "+ "2. Buy");
		System.out.println("");
		System.out.print( "    "+ "3. List Items");
		System.out.println("");
		System.out.print( "    "+ "4. Checkout");
		System.out.println("");
		
		
		System.out.print("Please choose the function you want: ");
		int response = 0;
		
		
		if (kb.hasNextInt()) 
            response = kb.nextInt();
		
		if ( response == 1)
		{
			shopSetup();
			intro();
		}
		
		if((response == 2) || (response == 3) || (response == 4))
		{
			
			if (names[0] == null)
			{
				System.out.println("");
				System.out.println("Shop is not yet set up yet!");
				System.out.println("");
				intro();
				
			}
			
			if (response == 2)
				buy();
		}
		
		if (response == 3)
			itemizedList();
		
		if (response == 4)
			checkOut();
				
	}
	public static void shopSetup()
	{
		//System.out.println(" ");
		System.out.print("Please enter the number of items to set up shop: ");
		int items = kb.nextInt();
		System.out.println(" ");
		names = new String [items];
		pricePerPackage = new double[items];
		discount = new int [items];
		productQuantity = new int [items];
		
		int num = items;
		for (int i = 1; i <= items; i++)
		{
			String suff = numSuffix(i);
			System.out.print("Please enter the name of the " + suff + " product: ");
			names[i-1] = kb.next();
			System.out.print("Please enter the per package price of " + names[i-1] + ": ");
			pricePerPackage[i-1] = kb.nextDouble();	
			System.out.print("Enter the number of packages ('x') to qualify for Special Discount (buy 'x' get 1 free) for " + names[i-1] + ", or 0 if no Special Discount offered: ");
			discount[i-1] = kb.nextInt();
			if ( discount[i-1] > 0)
				discount[i-1]++;
		}
		
		System.out.println("");
		System.out.print("Enter the dollar amount to qualify for Adiitional Discount (or 0 if none offered): ");
		additionalDiscountAmount = kb.nextDouble();
		if ( additionalDiscountAmount != 0)
		{
			System.out.print("Enter the Additional Discount rate (e.g., 0.1 for 10%): ");
			additionalDiscountRate = kb.nextDouble();
			if((additionalDiscountRate < 0) || (additionalDiscountRate > 0.5 ))
			{
				System.out.print("Invalid input. Enter a value > 0 and <= 0.5: ");
				additionalDiscountRate = kb.nextDouble();
			}
			System.out.println(" ");
			intro();
		}
		else
		{
			System.out.println(" ");
			intro();
		}
			
			
		
		
	}
	
	public static void buy()
	{
		System.out.println("");
		
		for (int i = 0; i< names.length; i++ )
		{
			//Enter the number of alpha packages to buy:
			System.out.print("Enter the number of " + names[i] + " to buy: " );
			productQuantity[i] = kb.nextInt();
			while (productQuantity[i] < 0 )
			{
				System.out.print("Invalid input. Enter a value >= 0: ");
				productQuantity[i]= kb.nextInt();
			}
			 
			 
		}
		System.out.println("");
		intro();
		
	}
	public static void itemizedList( )
	{
		Boolean notBought = false;
		
		if(names.length == 0)
		{
			System.out.print("Shop is not setup yet!");
			System.out.println(" ");
			intro();
		}
		
		for (int i = 0; i < productQuantity.length; i++)
		{
			if (productQuantity[i] == 0)
				notBought = true;
			else
			{
				notBought = false;
				break;
			}
				
		}
		
		if (notBought == true)
		{
			
			System.out.println("\nNo items were purchased.");
			System.out.println(" ");
			intro();
		}
			
		else
		{
			System.out.println("");
			for ( int i = 0; i < productQuantity.length; i++)
			{
				if(productQuantity[i] > 0)
				{
					if (productQuantity[i] > 1)
					{
						System.out.printf("%d%s%s%s%.2f%s%.2f\n", productQuantity[i] ," packages of ", names[i] , " @ $" , pricePerPackage[i] , " per pkg = $" , (pricePerPackage[i] * productQuantity[i]));
					}
					
					else
					{
						System.out.printf("%d%s%s%s%.2f%s%.2f\n", productQuantity[i] ," package of ", names[i] , " @ $" , pricePerPackage[i] , " per pkg = $" , (pricePerPackage[i] * productQuantity[i]));
					}
					
				}
			}
		}
		System.out.println(" ");
		intro();
	}
	
	public static void checkOut()
	{
		Boolean notBought = false;
		
		if(names.length == 0)
		{
			System.out.print("Shop is not setup yet!");
			System.out.println(" ");
			intro();
		}
		
		for (int i = 0; i < productQuantity.length; i++)
		{
			if (productQuantity[i] == 0)
				notBought = true;
			else
			{
				notBought = false;
				break;
			}	
		}
		
		if (notBought == true)
		{
			
			System.out.println("\nYou have not bought anything!");
			System.out.println(" ");
			intro();
		}
		double originalSubTotal = 0.0;
		
		for (int i = 0; i < pricePerPackage.length; i++)
		{
			originalSubTotal += (pricePerPackage[i] * productQuantity[i]);
		}
		
		//Original Sub Total:
		System.out.printf("\n%-36s%s%.2f", "Original Sub Total:",   " $" , originalSubTotal);
		
		//Special discount
		double specialDiscount = 0.0;
		for (int i = 0; i < discount.length; i++)
		{
			//System.out.println("Disount of i " + i + " " + discount[i]);
			if ( discount[i] > 0)
				specialDiscount += productQuantity[i] / discount[i] * pricePerPackage[i];
		}
		
		//System.out.println("Special discount" + specialDiscount);
		if ( specialDiscount != 0.0)
		{
			System.out.printf("\n%-36s%s%-4.2f", "Special Discount:",  "-$" , specialDiscount);	
		}
		
		else
		{
			System.out.printf("\n%-36s","No Special Discounts applied");
		}
		
		
		
		
		//New Sub Total
		double newSubTotal = 0.0;
		newSubTotal =  originalSubTotal - specialDiscount;
	    System.out.printf("\n%-36s%s%.2f", "New Sub Total:", " $" , newSubTotal);
		
		//Additional Discount
	    if (newSubTotal > additionalDiscountAmount )
	    {
	    	 	additionalDiscountAmount = newSubTotal * additionalDiscountRate;
	    	 	
	    	 	if ( additionalDiscountAmount > 0.0)
	    	 		System.out.printf("\n%-1s%d%s%14s%4.2f", "Additional ", ((int)(additionalDiscountRate * 100)), "% Discount:" ,  "  -$" , additionalDiscountAmount);
	    	 	else	
	    	 		System.out.printf("\n%-36s","You did not qualify for an Additional Discount");
	    }
	   
		//Final Sub Total
	    double finalSubtotal = 0.0;
	    finalSubtotal = newSubTotal - additionalDiscountAmount;
	    System.out.printf("\n%-36s%s%.2f", "Final Sub Total:"," $" , finalSubtotal);	
	    
		
		
		
		System.out.println ("\n\nThanks for coming!");
		
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.print("Would you like to re-run (1 for yes, 0 for no)? ");
		int response = kb.nextInt();
		
		if (response == 1)
		{
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
			intro();
		}
		
		else
		{
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
			System.exit(0);
		}
		
		System.out.println("");
	
	}
	
	public static String numSuffix(int i)
	{
		int rem = i % 10;
		
		switch(rem)
		{
			case 0:
			case 4:	
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return(i + "th");
				
			case 1:
				if( i % 100 != 11)
					return (i + "st");
				else
					return (i +"th");
				
			case 2:
				if( i % 100 != 12)
					return (i + "nd");
				else
					return(i + "th");
				
			case 3:
				if(i % 100 != 13)
					return(i + "rd");
				else
					return(i + "th");
				
			default:
				break;
		
		}
		return "";
	}
	

	public static void main (String [] args)
	{
		intro();
		kb.close();
	
	}

}

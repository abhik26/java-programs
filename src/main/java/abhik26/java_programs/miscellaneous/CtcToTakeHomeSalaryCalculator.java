package abhik26.java_programs.miscellaneous;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class CtcToTakeHomeSalaryCalculator {
	public static void main(String[] args) {
		int ctc = 15_00_000;
		calculateMonthlyTakeHomeSalary(ctc);
	}

	public static void calculateMonthlyTakeHomeSalary(int ctc) {

		// multipliers are respect to basic salary
		float grossMultiplier = 2f; // can be 2x to 2.5x of basic salary i.e. (basic is in the range of 40% to 50% of gross salary)
		float hraMultiplier = 0.4f; // can be 0.4x or 0.5x of basic salary i.e. (hra can be 40% or 50% of basic salary)
		float epfMultiplier = 0.12f;
		float gratuityMultiplier = 0.048f;

		int basicMonthly = Math.round((ctc / (grossMultiplier + epfMultiplier + gratuityMultiplier)) / 12);
		int hraMonthly = Math.round(basicMonthly * hraMultiplier);
		int grossMonthly = Math.round(basicMonthly * grossMultiplier);
		int specialMonthly = grossMonthly - (basicMonthly + hraMonthly);
		int epfMonthly = Math.round(basicMonthly * epfMultiplier);
		int gratuityMonthly = Math.round(basicMonthly * gratuityMultiplier);

		int basicAnnual = basicMonthly * 12;
		int hraAnnual = hraMonthly * 12;
		int specialAnnual = specialMonthly * 12;
		int grossAnnual = grossMonthly * 12;
		int epfAnnual = epfMonthly * 12;
		int gratuityAnnual = gratuityMonthly * 12;

		float incomeTaxAnnual = calculateIncomeTax(grossAnnual);
		float incomeTaxMonthly = incomeTaxAnnual / 12;

		String format = "%-15s\t%10s\t%10s\n";
		System.out.println();
		System.out.printf(format, "Component", "Monthly", "Annual");
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "Basic", basicMonthly, basicAnnual);
		System.out.printf(format, "HRA", hraMonthly, hraAnnual);
		System.out.printf(format, "Special", specialMonthly, specialAnnual);
		System.out.printf(format, "Gross", grossMonthly, grossAnnual);
		System.out.printf(format, "EPF", epfMonthly, epfAnnual);
		System.out.printf(format, "Gratuity", gratuityMonthly, gratuityAnnual);
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "Income tax", incomeTaxMonthly, incomeTaxAnnual);
		System.out.println(
				"Monthly salary after epf and tax deduction: " + (grossMonthly - epfMonthly - (incomeTaxAnnual / 12)));
		System.out.println("CTC provided: " + ctc + ", CTC calculated: " + (grossAnnual + epfAnnual + gratuityAnnual));
		System.out.println();
	}

	private static float calculateIncomeTax(int grossAnnual) {
		
		float incomeTax = 0f;
		int standarDeduction = 75_000;
		int marginalRelief = 75_000;
		int taxIncomeRebateLimit = 12_00_000;
		int healthAndEducationCessRate = 4;

		Map<Integer, Integer> incomeSlabRateMap = new HashMap<Integer, Integer>();
		incomeSlabRateMap.put(5, 4_00_000);
		incomeSlabRateMap.put(10, 8_00_000);
		incomeSlabRateMap.put(15, 12_00_000);
		incomeSlabRateMap.put(20, 16_00_000);
		incomeSlabRateMap.put(25, 20_00_000);
		incomeSlabRateMap.put(30, 24_00_000);

		LinkedList<Integer> slabRatesSorted = incomeSlabRateMap.keySet().stream().sorted((e1, e2) -> e2 - e1)
				.collect(Collectors.toCollection(LinkedList::new));

		int taxableIncome = grossAnnual - standarDeduction;

		if (taxableIncome - taxIncomeRebateLimit <= marginalRelief) {
			incomeTax = taxableIncome - taxIncomeRebateLimit;
		} else if (taxableIncome > taxIncomeRebateLimit) {
			while (!slabRatesSorted.isEmpty()) {
				int slabRate = slabRatesSorted.removeFirst();
				int slabRateIncome = incomeSlabRateMap.get(slabRate);

				if (taxableIncome > slabRateIncome) {
					int income = taxableIncome - slabRateIncome;
					incomeTax += (income * slabRate / 100f);
					taxableIncome = slabRateIncome;
				}
			}
		}

		incomeTax += (incomeTax * healthAndEducationCessRate / 100);
		
		return incomeTax;
	}
}
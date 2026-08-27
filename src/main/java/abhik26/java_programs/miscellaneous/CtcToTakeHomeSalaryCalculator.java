package abhik26.java_programs.miscellaneous;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class CtcToTakeHomeSalaryCalculator {

	// multipliers are respect to basic salary
	private static final float basicMultiplier = 0.5f; // can be 0.4x to 0.5x of CTC i.e. (basic is in the range of 40% to 50% of CTC)
	private static final float hraMultiplier = 0.5f; // can be 0.4x or 0.5x of basic salary i.e. (hra can be 40% or 50% of basic salary)
	private static final float epfMultiplier = 0.12f; // 12% of basic salary is contributed to EPF by employee and employer each. Total 24% of basic salary is contributed to EPF.
	private static final float gratuityMultiplier = 0.048f; // 4.8% of basic salary is contributed to gratuity by employer. Gratuity is applicable only if employee has worked for more than 5 years in the company.
	private static final float professionalTaxMonthly = 200f; // For Karnataka state, professional tax is 200 per month for salary above 15k

	public static void main(String[] args) {
		int ctc = 15_00_000; // 15 Lakhs Per Annum (LPA)
		calculateMonthlyTakeHomeSalary(ctc);
	}

	public static void calculateMonthlyTakeHomeSalary(int ctc) {

		float basicAnnual = ctc * basicMultiplier;
		float hraAnnual = basicAnnual * hraMultiplier;
		float epfAnnual = basicAnnual * epfMultiplier;
		float gratuityAnnual = basicAnnual * gratuityMultiplier;
		float grossAnnual = ctc - (epfAnnual + gratuityAnnual);
		float specialAnnual = grossAnnual - (basicAnnual + hraAnnual);

		float basicMonthly =  basicAnnual / 12;
		float grossMonthly = grossAnnual / 12;
		float hraMonthly = hraAnnual / 12;
		float specialMonthly = specialAnnual / 12;
		float epfMonthly = epfAnnual / 12;
		float gratuityMonthly = gratuityAnnual / 12;

		float incomeTaxAnnual = calculateIncomeTax(grossAnnual);
		float incomeTaxMonthly = incomeTaxAnnual / 12;

		String format = "%-20s %15s %15s\n";
		String line = "=====================================================";
		System.out.println(line);
		System.out.printf(format, "Component", "Monthly", "Annual");
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "Basic", basicMonthly, basicAnnual);
		System.out.printf(format, "House Rent Allowance", hraMonthly, hraAnnual);
		System.out.printf(format, "Special Allowance", specialMonthly, specialAnnual);
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "Gross", grossMonthly, grossAnnual);
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "EPF", epfMonthly, epfAnnual);
		System.out.printf(format, "Gratuity", gratuityMonthly, gratuityAnnual);
		System.out.printf(format, "-", "-", "-");
		System.out.printf(format, "Income tax", incomeTaxMonthly, incomeTaxAnnual);
		System.out.printf(format, "Professional tax", professionalTaxMonthly, professionalTaxMonthly * 12);
		System.out.printf(format, "-", "-", "-");
		System.out.println(
				"In hand monthly salary after deductions: " + (grossMonthly - epfMonthly - incomeTaxMonthly - professionalTaxMonthly));
		System.out.println("CTC provided: " + ctc + ", CTC calculated: " + (grossAnnual + epfAnnual + gratuityAnnual));
		System.out.println(line);
	}

	private static float calculateIncomeTax(float grossAnnual) {
		
		float incomeTax = 0f;
		int standarDeduction = 75_000;
		int marginalRelief = 75_000;
		int taxIncomeRebateLimit = 12_00_000;
		float healthAndEducationCessMultiplier = 0.04f;

		Map<Integer, Integer> incomeSlabRateMap = new HashMap<Integer, Integer>();
		incomeSlabRateMap.put(5, 4_00_000);
		incomeSlabRateMap.put(10, 8_00_000);
		incomeSlabRateMap.put(15, 12_00_000);
		incomeSlabRateMap.put(20, 16_00_000);
		incomeSlabRateMap.put(25, 20_00_000);
		incomeSlabRateMap.put(30, 24_00_000);

		LinkedList<Integer> slabRatesSorted = incomeSlabRateMap.keySet().stream().sorted((e1, e2) -> e2 - e1)
				.collect(Collectors.toCollection(LinkedList::new));

		float taxableIncome = grossAnnual - standarDeduction;

		if (taxableIncome > taxIncomeRebateLimit) {
			if (taxableIncome - taxIncomeRebateLimit <= marginalRelief) {
				incomeTax = taxableIncome - taxIncomeRebateLimit;
			} else {
				while (!slabRatesSorted.isEmpty()) {
					int slabRate = slabRatesSorted.removeFirst();
					int slabRateIncome = incomeSlabRateMap.get(slabRate);

					if (taxableIncome > slabRateIncome) {
						float income = taxableIncome - slabRateIncome;
						incomeTax += (income * slabRate / 100f);
						taxableIncome = slabRateIncome;
					}
				}
			}
		}

		incomeTax += (incomeTax * healthAndEducationCessMultiplier);
		
		return incomeTax;
	}
}
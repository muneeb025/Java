import java.util.Arrays;

public class SentimentAnalyzer {

	public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
			String[] negOpinionWords) {

		int[] featureOpinions = new int[featureSet.length];
		int count = 0;
		for (String[] featureSet1 : featureSet) {
			for (String feature : featureSet1) {
				int method1 = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
				featureOpinions[count] = method1;
				if (method1 > 0 || method1 < 0) {
					break;
				}
			}
			count += 1;
		}
		return featureOpinions;
	}

	private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords,
			String[] negOpinionWords) {

		int opinion2 = 0;
		String review1 = review.toLowerCase();

		for (String opinion : posOpinionWords) {

			String pattern = feature + " was " + opinion;
			String pattern1 = opinion + " " + feature;

			if (review1.contains(pattern)) {

				int opinion1 = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
				opinion2 = opinion1;
				break;
			}

			else if (review1.contains(pattern1)) {
				int opinion1 = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
				opinion2 = opinion1;
				break;
			}
		}

		for (String opinion : negOpinionWords) {
			String pattern = feature + " was " + opinion;
			String pattern1 = opinion + " " + feature;

			if (review1.contains(pattern)) {

				int opinion1 = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
				opinion2 = opinion1;
				break;

			}

			else if (review1.contains(pattern1)) {
				int opinion1 = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
				opinion2 = opinion1;
				break;
			}

		}

		return opinion2;
	}

	private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords,
			String[] negOpinionWords) {

		int opinion = 0;
		String review1 = review.toLowerCase();

		for (String opinion1 : posOpinionWords) {

			String pattern = feature + " was " + opinion1;
//			String pattern1 = opinion1 + " " + feature;

			if (review1.contains(pattern)) {
				opinion += 1;
				break;
			}
		}

		for (String opinion1 : negOpinionWords) {
			String pattern = feature + " was " + opinion1;
//			String pattern1 = opinion1 + " " + feature;

			if (review1.contains(pattern)) {

				opinion -= 1;
				break;
			}
		}
		return opinion;
	}

	private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
			String[] negOpinionWords) {
		int opinion = 0;
		String review1 = review.toLowerCase();

		for (String opinion1 : posOpinionWords) {
			String pattern1 = opinion1 + " " + feature;

			if (review1.contains(pattern1)) {
				opinion += 1;
				break;
			}
		}

		for (String opinion1 : negOpinionWords) {
			String pattern1 = opinion1 + " " + feature;

			if (review1.contains(pattern1)) {
				opinion -= 1;
				break;
			}
		}

		return opinion;
	}

	public static void main(String[] args) {
		String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

//		 String review = "Sorry OG, but you just lost some loyal customers. Horrible"
//		 + " service, no smile or greeting just attitude. The breadsticks were stale and"
//		 + " burnt, appetizer was cold and the food came out before the salad.";

		String[][] featureSet = { { "ambiance", "ambience", "atmosphere", "decor" },
				{ "dessert", "ice cream", "desert" }, { "food" }, { "soup" },
				{ "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
		String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
				"delicious" };
		String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

		int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
		System.out.println("Opinion On Features: " + Arrays.toString(featureOpinions));
	}
}
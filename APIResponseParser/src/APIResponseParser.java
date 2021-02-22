public class APIResponseParser {

	/**
	 * 
	 * Parses the input text and returns a Book instance containing
	 * 
	 * the parsed data.
	 * 
	 * @param response text to be parsed
	 * 
	 * @return Book instance containing parsed data
	 * 
	 */

	public static Book parse(String response) {

		Book book = new Book();

		String endRule = "<";

		String startRule = "<title>";

		String title = parse(response, startRule, endRule);

		book.setTitle(title);

		System.out.println(book.getTitle());

		// Your code

		startRule = "<name>";

		String authorName = parse(response, startRule, endRule);

		book.setAuthor(authorName);

		System.out.println(book.getAuthor());

		startRule = "<original_publication_year type=\"integer\">";

		String publicationYear = parse(response, startRule, endRule);

		book.setPublicationYear(Integer.parseInt(publicationYear));

		System.out.println(book.getPublicationYear());

		startRule = "<average_rating>";

		String averageRating = parse(response, startRule, endRule);

		book.setAverageRating(Double.parseDouble(averageRating));

		System.out.println(book.getAverageRating());

		startRule = "<ratings_count type=\"integer\">";

		String ratingsCount = parse(response, startRule, endRule);

		book.setRatingsCount(Integer.parseInt(ratingsCount.replaceAll(",", "")));

		System.out.println(book.getRatingsCount());

		startRule = "<image_url>";

		String image = parse(response, startRule, endRule);

		book.setImageUrl(image);

		System.out.println(book.getImageUrl());

		return book;

	}

	// write overloaded parse method with the 3 parameters response, startRule,
	// endRule

	private static String parse(String response, String startRule, String endRule) {
		 
		response = response.substring(response.indexOf(startRule) + startRule.length());
 
		return response.substring(0, response.indexOf(endRule));

	}

	public static void main(String[] args) {

		String response = "<work>" +

				"<id type=\"integer\">2361393</id>" +

				"<books_count type=\"integer\">813</books_count>" +

				"<ratings_count type=\"integer\">1,16,315</ratings_count>" +

				"<text_reviews_count type=\"integer\">3439</text_reviews_count>" +

				"<original_publication_year type=\"integer\">1854</original_publication_year>" +

				"<original_publication_month type=\"integer\" nil=\"true\"/>" +

				"<original_publication_day type=\"integer\" nil=\"true\"/>" +

				"<average_rating>3.79</average_rating>" +

				"<best_book type=\"Book\">" +

				"<id type=\"integer\">16902</id>" +

				"<title>Walden</title>" +

				"<author>" +

				"<id type=\"integer\">10264</id>" +

				"<name>Henry David Thoreau</name>" +

				"</author>" +

				"<image_url>" +

				"http://images.gr-assets.com/books/1465675526m/16902.jpg" +

				"</image_url>" +

				"<small_image_url>" +

				"http://images.gr-assets.com/books/1465675526s/16902.jpg" +

				"</small_image_url>" +

				"</best_book>" +

				"</work>";

		APIResponseParser.parse(response);

	}

}
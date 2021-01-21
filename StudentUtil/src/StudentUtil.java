import java.util.Arrays;

public class StudentUtil {

	public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {

		double[] studentGpa = new double[studentIdList.length];
		double gpa = 0;
		int count = 0;

		for (char[] grades : studentsGrades) {
			for (char grade : grades) {
				if (grade == 'A') {
					gpa += 4;
				}
				else if (grade == 'B') {
					gpa += 3;
				}
				else {
					gpa += 2;
				}
			}
			
			double calGpa = gpa / grades.length;
			studentGpa[count] = calGpa;
			count += 1;
			gpa = 0;
		}
		return studentGpa;
	}

	public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
		
		if (lower < 0 || higher < 0 || lower > higher){
			return null;
		}
		else {
			
		int [] students = new int[] {};
		int count = 0;
		// invoke calculateGPA
		
		double[] studentGpa = calculateGPA(studentIdList, studentsGrades);
		
		for(double gpa : studentGpa) {
			if(gpa > lower && gpa < higher) {
				for(int studentId:studentIdList) {
					students[count] = studentId;
				}
			}
			count+=1;
	}

		// construct the result array and return it. You would need an extra for loop to
		// compute the size of the resulting array
		
		return students;
		}
	}

	public static void main(String[] args) {

		double lower = 3.2;
		double higher = 3.5;
		int[] studentIdList = { 1001, 1002, 1003 };
		char[][] studentsGrades = { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' }, { 'A', 'B', 'C' } };

		double[] studentGpa = calculateGPA(studentIdList, studentsGrades);
		System.out.println(Arrays.toString(studentGpa));

		int[] students = getStudentsByGPA(lower, higher, studentIdList, studentsGrades);
		System.out.println(Arrays.toString(students));
	}
}

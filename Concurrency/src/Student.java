public class Student {
  private Tutor tutor;

  public Student(Tutor tutor) {
    this.tutor = tutor;
  }

  public void startStudy() {
    System.out.println("Student is studying");
  }

  public void handInAssignment() {
    tutor.getProgressReport();
    System.out.println("Student handed in assignment");
  }
}

export class Assessment {
    assessmentId?: number;
  courseId?: number;
  instructorId?: number;
  maxScore?: number;
  questions: Question[] = [];

}

export class Question {
    questionId?: number;
    questionText?: string;
    options: string[] = [];
    correctAns?: string;
  }

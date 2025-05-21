export interface Submission {
    submissionId?: number;
    assessmentId: number;
    studentId: number;
    ans: { [key: number]: string };
    score?: number;
  }
import {ParentTask} from './parentTask';

export class Task {
  id: number;
  task: string;
  priority: number;
  startDate: Date;
  endDate: Date;
  parentTask: ParentTask;
}

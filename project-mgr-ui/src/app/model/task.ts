import {ParentTask} from './parentTask';
import { Project } from './project';

export class Task {
  id: number;
  task: string;
  priority: number;
  startDate: Date;
  endDate: Date;
  parentTask: ParentTask;
  project: Project;
  userId: number;
  projId: number;
  status: string;
}

import { TaskFilter } from './task-filter.pipe';

describe('TaskFilter', () => {
  it('create an instance', () => {
    const pipe = new TaskFilter();
    expect(pipe).toBeTruthy();
  });
});

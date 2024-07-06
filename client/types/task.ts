import { Subtask } from "./subtask"

export enum TaskStatusType {
    PLANNED,
    PROGRESS,
    COMPLETED
}

export enum PriorityType {
    NORMAL,
    CRITICAL
}

export type Task = {
  id: number
  title: string
  status: TaskStatusType
  description: string
  estimatedTime: string
  actualTime: string
  dueDate: string
  workspaceId: number
  userId: number
  priority: PriorityType
  createdAt: string
  updatedAt: string
  imageURL: string
  subTasks: Subtask[]
}

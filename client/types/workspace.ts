import { Task } from "./task"

export type Workspace = {
  id: number
  name: string
  description: string
  createdAt: string
  updatedAt: string
  tasks: Task[];
}

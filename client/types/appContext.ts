import { Dispatch, SetStateAction } from "react"
import { User } from "./user"

export type AppContextType = {
  user: User | undefined,
  setUser: Dispatch<SetStateAction<User | undefined>>
}

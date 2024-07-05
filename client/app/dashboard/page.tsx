'use client'

import DashboardLayout from './layout'
import Workspaces from './workspaces/page'

export default function Home() {
  return (
    <DashboardLayout>
      <Workspaces />
    </DashboardLayout>
  )
}

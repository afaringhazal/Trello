'use client'

import Workspaces from './workspaces/page'
import DashboardLayout from './layout'

export default function Home() {
    return (
      <DashboardLayout>
        <Workspaces />
      </DashboardLayout>
    )
}

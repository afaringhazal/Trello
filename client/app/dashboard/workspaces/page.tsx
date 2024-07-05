'use client'

import * as React from 'react';
import {
  Box,
  Drawer,
  CssBaseline,
  Toolbar,
  List,
  Typography,
  Divider,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Container,
  ListSubheader
} from '@mui/material'

import DeveloperBoard from '@mui/icons-material/DeveloperBoard'
import DesignServices from '@mui/icons-material/DesignServices'
import Home from '@mui/icons-material/Home'

import { Workspace } from '@/types/workspace'
import { useEffect, useState } from 'react';
import { axios } from '@/utils';
import { AxiosError } from 'axios';

import Boards from '@/components/Boards';

const mainPanels = [
  { name: 'Boards', icon: DeveloperBoard, component: Boards },
  { name: 'Templates', icon: DesignServices, component: <h1>Templates</h1> },
  { name: 'Home', icon: Home, component: <h1>Home</h1> }
]

const drawerWidth = 240

export default function Workspaces() {
  const [workspaces, setWorkspaces] = useState<Workspace[]>()
  const [selectedPane, setSelectedPane] = useState<React.ReactNode>(Boards)
  useEffect(() => {
    getWorkspaces().then(setWorkspaces)
  }, [])
  return (
    <Container>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <Drawer
          variant="permanent"
          sx={{
            zIndex: () => - 1,
            width: drawerWidth,
            flexShrink: 0,
            '& .MuiDrawer-paper': {
              width: drawerWidth, boxSizing: 'border-box', border: 'none'
            },
            '& .MuiDrawer-root': {
              position: 'relative'
            },
            '& .MuiPaper-root': {
              position: 'relative'
            },
          }}
        >
          <Toolbar />
          <Box sx={{ overflow: 'auto' }}>
            <List>
              {mainPanels?.map(panel => (
                <ListItem key={panel.name} disablePadding onClick={() => setSelectedPane(panel?.component)}>
                  <ListItemButton>
                    <ListItemIcon>
                      <panel.icon />
                    </ListItemIcon>
                    <ListItemText primary={panel.name} />
                  </ListItemButton>
                </ListItem>
              ))}
            </List>
            <Divider />
            <List
              subheader={
                <ListSubheader>Workspaces</ListSubheader>
              }
            >
              {workspaces?.map((ws) => (
                <ListItem key={ws.id} disablePadding>
                  <ListItemButton>
                    {ws.name}
                    <ListItemText primary={ws.id} />
                  </ListItemButton>
                </ListItem>
              ))}
            </List>
          </Box>
        </Drawer>
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Toolbar />
          {selectedPane}
        </Box>
      </Box>
    </Container>
  )
  async function getWorkspaces(): Promise<Workspace[] | undefined> {
    try {
      return (await axios.get('/workspace')).data
    } catch (e: AxiosError | any) {
      console.log(e, 'error getting workspaces')
    }
  }
}

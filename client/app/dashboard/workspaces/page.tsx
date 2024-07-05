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

const mainPanels = [{ name: 'Boards', icon: DeveloperBoard }, { name: 'Templates', icon: DesignServices }, { name: 'Home', icon: Home }]
const workspaces: Workspace[] = []

const drawerWidth = 440

export default function Workspaces() {
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
                <ListItem key={panel.name} disablePadding>
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
                <ListItem key={ws.href} disablePadding>
                  <ListItemButton>
                    {/* <ListItemIcon> */}
                    {/*   {index % 2 === 0 ? <DeveloperBoard /> : <DesignServices />} */}
                    {/* </ListItemIcon> */}
                    <ListItemText primary={ws.name} />
                  </ListItemButton>
                </ListItem>
              ))}
            </List>
          </Box>
        </Drawer>
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Toolbar />
          <Typography paragraph>
            Lorem ipsum
          </Typography>
        </Box>
      </Box>
    </Container>
  );
}

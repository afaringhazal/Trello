import * as React from 'react';
import BoardCard from './card';
import { Box, Divider } from '@mui/material';

const fakeTitles = [
  { title: 'Project Management', description: 'test' },
  { title: 'Kanban Board', description: 'a Kanban board' },
]

export default function Boards() {
  return (
    <div style={{ display: 'flex', flexDirection: 'column' }}>
      <Box component="h1">Most popular templates</Box>
      <Box sx={{ display: 'flex', flexDirection: 'row' }}>
        <BoardCard
          title={fakeTitles[0].title}
          description={fakeTitles[0].description}
          actionButton={undefined} />
        <Divider orientation='vertical' flexItem sx={{ marginRight: '5px' }} />
        <BoardCard
          title={fakeTitles[1].title}
          description={fakeTitles[1].description}
          actionButton={undefined} />
      </Box>
      <Divider orientation='horizontal' flexItem />
    </div>
  );
}

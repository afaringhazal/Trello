import * as React from 'react';
import BoardCard from './card';
import { Box, Divider } from '@mui/material';

const fakeTemplateTitles = [
  { title: 'Project Management', description: 'Project management template' },
  { title: 'Kanban Board', description: 'A Kanban board template' },
]
const fakeRecentsTitles = [
  { title: 'U Frontend', description: '' },
  { title: 'U Coordination', description: '' },
]

export default function Boards() {
  return (
    <div style={{ display: 'flex', flexDirection: 'column' }}>
      <Box component="h1">
        Most popular templates
        <Divider component='h1' orientation='horizontal' flexItem sx={{ marginBottom: '20px' }} />
      </Box>
      <Box sx={{ display: 'flex', flexDirection: 'row', paddingBottom: '50px' }}>
        <BoardCard
          title={fakeTemplateTitles[0].title}
          description={fakeTemplateTitles[0].description}
          actionButton={undefined} />
        <Divider orientation='vertical' flexItem sx={{ marginRight: '5px' }} />
        <BoardCard
          title={fakeTemplateTitles[1].title}
          description={fakeTemplateTitles[1].description}
          actionButton={undefined} />
      </Box>
      <Box component="h2">
        Recently Viewed
        <Divider component='h2' orientation='horizontal' flexItem sx={{ marginBottom: '20px' }} />
      </Box>
      <Box sx={{ display: 'flex', flexDirection: 'row' }}>
        <BoardCard
          title={fakeRecentsTitles[0].title}
          description={fakeRecentsTitles[0].description}
          actionButton={undefined} />
        <Divider orientation='vertical' flexItem sx={{ marginRight: '5px' }} />
        <BoardCard
          title={fakeRecentsTitles[1].title}
          description={fakeRecentsTitles[1].description}
          actionButton={undefined} />
      </Box>
    </div>
  );
}

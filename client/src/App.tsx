import {useEffect} from 'react';
import { styled } from 'styled-components';
import Navigation from './components/header/Navigation';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import constants from 'src/common/constants';
import SimulationPage from './pages/simulation/SimulationPage';
import { QueryClient, QueryClientProvider } from 'react-query';
import HomePage from './pages/home/HomePage';


const Container = styled.div`
  min-height: 100vh;
  display: flex;
  
  @media(max-width: ${constants.ui.maxWidth}) {
    flex-direction: column;
  }
`

const Content = styled.div`
  flex: 1;
`

const queryClient = new QueryClient()

const App = () => {

  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <Container>
          <Navigation />
          
          <Content>
            <Routes>
              <Route path='/' Component={HomePage}/>
              <Route path='/simulation/:simulationId' Component={SimulationPage}/>
            </Routes>
          </Content>
          
        </Container>
      </Router>
    </QueryClientProvider>
  );
}

export default App;

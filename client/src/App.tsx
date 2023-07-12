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
  useEffect(() => {
    // fetch("http://localhost:8080/system/scan/pickup", {
    //   method: "POST", 
    //   headers: { 'Content-Type': 'application/json' },
    //   body : JSON.stringify({
    //     floor: 2,
    //     direction: "UP"
    //   })
    // })
    // .then( async response => {
    //   console.log(await response.json())
    // })

    // fetch("http://localhost:8080/simulation", {
    //   method: "POST", 
    //   headers: { 'Content-Type': 'application/json' },
    //   body : JSON.stringify({
    //     systemVariant: "SCAN",
    //     factoryVariant: "DEFAULT",
    //     strategyVariant: "DEFAULT",
    //     highestFloor: 10,
    //     lowestFloor: -10,
    //     elevatorCount: 16,
    //     delay: 1000,
    //   })
    // })
    // .then( async response => {
    //   console.log(await response.json())
    // })
  }, [])

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

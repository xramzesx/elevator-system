import {useEffect} from 'react';
import { styled } from 'styled-components';
import Navigation from './components/header/Navigation';
import ElevatorSimulation from './components/elevator/ElevatorSimulation';


const Container = styled.div`
  min-height: 100vh;
  display: flex;
  
  @media(max-width: ${constants.ui.maxWidth}) {
    flex-direction: column;
  }
`

const App = () => {
  useEffect(() => {
    fetch("http://localhost:8080/system/scan/pickup", {
      method: "POST", 
      headers: { 'Content-Type': 'application/json' },
      body : JSON.stringify({
        floor: 2,
        direction: "UP"
      })
    })
    .then( async response => {
      console.log(await response.json())
    })
  }, [])

  return (
    <Container>
      <Navigation />
      <ElevatorSimulation simulationId={0}/>
    </Container>
  );
}

export default App;

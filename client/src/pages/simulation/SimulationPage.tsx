import constants from "src/common/constants";
import { useQuery } from "react-query";
import { useParams } from "react-router-dom"
import ElevatorSimulation from "src/components/elevator/ElevatorSimulation"
import useSimulationConfig from "src/hooks/useSimulationConfig";
import { SimulationConfig } from "@common/types";
import { styled } from "styled-components";
import { CircularProgress } from "@mui/material";
import ErrorIcon from '@mui/icons-material/Error';

const Container = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    flex-direction: column;
`

const Title = styled.h1`
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 1rem;
`

const Message = styled.p`
    
`

const SimulationPage = () => {
    const { simulationId } = useParams<{simulationId: string}>()

    const { data, isLoading, isError, error } = useSimulationConfig(simulationId ?? "")
        
    /// TODO: make better loading page and error page
    if (isLoading) {
        return (
            <Container>
                <Title> 
                    <CircularProgress />
                    Loading...
                </Title>
            </Container>
        );
    }

    if (isError) {
        return (
            <Container>
                <Title>
                    <ErrorIcon fontSize="large"/>
                    Error
                </Title> 
                <Message>
                    {error?.message}
                </Message>
            </Container>
        );
    }

    return (
        <>
            <ElevatorSimulation
                simulationId={simulationId ?? ""}
                lowestFloor={data!.lowestFloor}
                highestFloor={data!.highestFloor}
            />
        </>
    )
}

export default SimulationPage
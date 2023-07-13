import { SimulationConfig } from "@common/types"
import useSimulations from "src/hooks/useSimulations"
import { styled } from "styled-components"
import SessionsTable from "./SessionsTable"
import constants from "src/common/constants"
import { useMemo } from "react"
import { Button } from "@mui/material"
import { Link } from 'react-router-dom'

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    flex: 1;
    height: 100%;
    gap: 1rem;
`

const ContentWrapper = styled.div`
    max-width: ${constants.ui.maxWidth};
    width: 100%;
`

const HomePage = (props: any) => {
    const {data, isLoading, isError} = useSimulations()
    console.log('simulations', data)
    
    const content = useMemo(() => !(data ?? new Map<string, SimulationConfig>()).size
        ? (
            <Button variant="contained" component={Link} to="/new">
                Create new simulation
            </Button>
        ) : (
            <ContentWrapper>
                <SessionsTable
                    configs={data ?? new Map<string, SimulationConfig>()}
                />
            </ContentWrapper>
        ),[
        data
    ])

    return (
        <Container>
            <h1>Hi!</h1>
            <p>
                Create new simulation or choose one from list below!
            </p>
            {content}
        </Container>
    )
}

export default HomePage


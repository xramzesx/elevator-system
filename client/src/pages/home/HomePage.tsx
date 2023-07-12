import { SimulationConfig } from "@common/types"
import { useMemo } from "react"
import useSimulations from "src/hooks/useSimulations"
import { styled } from "styled-components"
import SessionsTable from "./SessionsTable"
import constants from "src/common/constants"

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    flex: 1;
    height: 100%;
    gap: 1rem;
`

const Title = styled.h1`
    
`

const Description = styled.p`
    
`

const TableWrapper = styled.div`
    max-width: ${constants.ui.maxWidth};
    width: 100%;
`

const HomePage = (props: any) => {
    const {data, isLoading, isError} = useSimulations()
    console.log('simulations', data)
    return (
        <Container>
            <Title>Hi!</Title>
            <Description>
                Create new simulation or choose one from list below!
            </Description>
            <TableWrapper>
                <SessionsTable
                    configs={data ?? new Map<string, SimulationConfig>()}
                />
            </TableWrapper>
        </Container>
    )
}

export default HomePage


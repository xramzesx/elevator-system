import styled from "styled-components"
import constants from "src/common/constants"
import { ElevatorStatus } from "@common/types"
import { useMemo } from "react"
import ElevatorDisplay from "./ElevatorDisplay"

const Component = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    position: sticky;
    top: 0;
    z-index: 1;
`

const Container = styled.div`
    display: grid;
    grid-template-columns: 1fr auto;
    max-width: ${constants.ui.maxWidth};
    width: 100%;
`

const BlankDiv = styled.div`
    width: ${constants.elevator.panel.width};
`

const Tracks = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    justify-content: space-around;
`


interface IElevatorDisplays {
    statusList: ElevatorStatus[]
}

const ElevatorDisplays = (props: IElevatorDisplays) => {
    const displays = useMemo(() => props.statusList.map((status, index) => (
        <ElevatorDisplay
            status={status}
            key={index}
        />
    )), [props.statusList])

    return (
        <Component>
            <Container>
                <Tracks>
                    {displays}
                </Tracks>
                <BlankDiv/>
            </Container>
        </Component>
    )
}

export default ElevatorDisplays
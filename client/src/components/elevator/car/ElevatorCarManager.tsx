import { ElevatorStatus } from "src/common/types"
import { useMemo } from "react"
import styled from "styled-components"
import ElevatorCarTrack from "./ElevatorCarTrack"
import constants from "src/common/constants"


const ElevatorIndicator = styled.div`
`

const Component = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
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

interface IElevatorCarManager {
    statusList: ElevatorStatus[];
    lowestFloor: number;
    highestFloor: number;
}


const ElevatorCarManager = (props: IElevatorCarManager) => {

    const tracks = useMemo(() => props.statusList.map((status, index) => (
        <ElevatorCarTrack
            status={status}
            lowestFloor={props.lowestFloor}
            highestFloor={props.highestFloor}
            key={index}
        />
    )), [props.statusList])
    
    return (
        <Component>
            <Container>
                <Tracks>
                    {tracks}
                </Tracks>
                <BlankDiv/>
            </Container>
        </Component>
    )
}

export default ElevatorCarManager
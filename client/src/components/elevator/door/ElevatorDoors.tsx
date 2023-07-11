import { ElevatorStatus } from "../../../common/types"
import styled from "styled-components"
import { useMemo } from "react"
import ElevatorDoor from "./ElevatorDoor"

const Container = styled.div`
    display: flex;
    /* justify-content: center; */
    justify-content: space-around;
    align-items: center;
`

interface IElevatorDoors {
    floor: number,
    statusList: ElevatorStatus[],
}

const ElevatorDoors = (props: IElevatorDoors) => {
    
    const doors = useMemo(
        () => props.statusList.map((status, index) => (
            <ElevatorDoor
                isOpen={status.isOpen && props.floor === status.current}
                key={index}
            />
        )),[props.statusList]
    )

    return (
        <Container>
            {doors}
        </Container>
    )
}

export default ElevatorDoors
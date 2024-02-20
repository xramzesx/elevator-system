import { pickupInside } from "src/common/utils"
import { useCallback } from "react"
import { useParams } from "react-router-dom"
import constants from "src/common/constants"
import styled from "styled-components"


interface IDoor {
    $isOpen: boolean
}

const Door = styled.div<IDoor>`
    background: ${props => props.$isOpen ? "#00f4" : "#eeee"};
    width: 100%;
    max-width: ${constants.elevator.car.width};
    height: ${constants.elevator.car.height};
    transition: background .25s ease;
    cursor: pointer;
`

interface IElevatorDoor {
    isOpen: boolean;
    floor: number;
    elevatorId: number;
}

const ElevatorDoor = (props: IElevatorDoor) => {
    const { simulationId } = useParams<{simulationId: string}>()

    const handleClick = useCallback( async () => {
        console.log(simulationId, props.floor, props.elevatorId)
        await pickupInside(simulationId ?? "", props.elevatorId, props.floor)
    },[simulationId, props.floor, props.elevatorId])
    
    return (
        <Door $isOpen={props.isOpen} onClick={handleClick}/>
    )
}

export default ElevatorDoor
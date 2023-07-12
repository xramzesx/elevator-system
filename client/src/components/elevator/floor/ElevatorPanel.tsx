import constants from "src/common/constants"
import styled from "styled-components"
import { IconButton } from '@mui/material'

import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import { useMemo } from "react";
import { ElevatorDirection, ElevatorRequest } from "src/common/types";

const Container = styled.div`
    background: #0001;
    width: ${constants.elevator.car.width};
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`

interface IElevatorPanel {
    isHighest?: boolean;
    isLowest?: boolean;
    floor: number;
    onPickup: (request: ElevatorRequest) => void
}

const ElevatorPanel = (props: IElevatorPanel) => {

    const handleUpward = () => props.onPickup({
        floor: props.floor, 
        direction: ElevatorDirection.UP
    })
    

    const handleDownward = () => props.onPickup({
        floor: props.floor, 
        direction: ElevatorDirection.DOWN
    })

    const upwardButton = useMemo(() => props.isHighest ? null : (
        <IconButton onClick={handleUpward}>
            <KeyboardArrowUpIcon/>
        </IconButton>
    ),[props.isHighest])

    const downwardButton = useMemo(() => props.isLowest ? null : (
        <IconButton onClick={handleDownward}>
            <KeyboardArrowDownIcon/>
        </IconButton>
    ), [props.isLowest])

    return (
        <Container>
            {upwardButton}
            {downwardButton}
        </Container>
    )
}

export default ElevatorPanel
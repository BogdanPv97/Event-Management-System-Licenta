export class Bill {

    constructor(public billId: number,
                public userId: number,
                public description: string,
                public issuedDate: Date,
                public amount: DoubleRange,
                public paid: boolean
        ){}
}

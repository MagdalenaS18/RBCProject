export class Account {
    constructor(
        public id: number | null,
        public name: string,
        public currency: string,
        public balance: number
    ){}
}

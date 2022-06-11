import { City } from './city';

export class User {
  constructor(
    public firstName: string,
    public lastName: string,
    public email: string,
    public username: string,
    public password: string,
    public role: string,
    public age: number,
    public newsletter: boolean,
    public city: string
  ) {}
}
